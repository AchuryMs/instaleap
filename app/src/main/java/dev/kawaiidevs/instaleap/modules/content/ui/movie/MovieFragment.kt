package dev.kawaiidevs.instaleap.modules.content.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import dev.kawaiidevs.instaleap.R
import dev.kawaiidevs.instaleap.adapter.GenericAdapter
import dev.kawaiidevs.instaleap.adapter.ItemDataAbstract
import dev.kawaiidevs.instaleap.components.CustomDialogFragment
import dev.kawaiidevs.instaleap.data.network.NetworkStatus
import dev.kawaiidevs.instaleap.data.network.NetworkStatusHelper
import dev.kawaiidevs.instaleap.databinding.FragmentMultimediaBinding
import dev.kawaiidevs.instaleap.modules.content.entities.MultimediaItemModelUI
import dev.kawaiidevs.instaleap.modules.content.ui.multimedia.MultimediaViewModel
import dev.kawaiidevs.instaleap.modules.content.view.ContentItemView
import dev.kawaiidevs.instaleap.modules.content.view.MultimediaUiState
import dev.kawaiidevs.instaleap.utils.OnSingleClickListener
import dev.kawaiidevs.instaleap.utils.hideKeyboard
import dev.kawaiidevs.instaleap.utils.toggleVisibility
import dev.kawaiidevs.instaleap.utils.viewBinding

const val TAG = "MovieFragment"
const val TYPE = "MOVIE"

@AndroidEntryPoint
class MovieFragment : Fragment() {

    companion object {
        fun newInstance() = MovieFragment()
    }

    private val binding by viewBinding<FragmentMultimediaBinding>()
    private val viewModel by viewModels<MultimediaViewModel>()
    private val multimediaAdapter by lazy {
        GenericAdapter { parent, _ ->
            ContentItemView( 
                parent.context,
                ::onItemSelected
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_multimedia, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            viewModel = this@MovieFragment.viewModel
            lifecycleOwner = viewLifecycleOwner
        }
        initViews()
    }

    private fun initViews() {
        binding.rvContentList.adapter = multimediaAdapter

        hideAndClear()
        observeUiStates()
        networkStatusVisibility()
        getAllContent()
    }

    private fun observeUiStates() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.multimediaUiState.collect { searchUiState ->
                when (searchUiState) {
                    MultimediaUiState.Loading -> {
                        binding.progress.toggleVisibility(show = true)
                    }

                    is MultimediaUiState.Error, MultimediaUiState.ShowNoConnectivityError -> {
                        binding.progress.toggleVisibility(show = false)
                        binding.includeGenericErrorView.root.toggleVisibility(show = false)
                        showDialog(
                            resources.getString(R.string.title_generic_exception_error),
                            resources.getString(R.string.body_generic_exception_error),
                            R.drawable.ic_error
                        )
                    }

                    MultimediaUiState.Default -> {
                        binding.progress.toggleVisibility(show = false)
                        binding.includeGenericErrorView.root.toggleVisibility(show = false)
                    }

                    MultimediaUiState.NoDataFound -> {
                        showDialog(
                            resources.getString(R.string.title_generic_exception_error),
                            resources.getString(R.string.empty_query_label),
                            R.drawable.ic_error
                        )
                        binding.rvContentList.toggleVisibility(false)
                        binding.includeGenericErrorView.root.toggleVisibility(show = true)
                        binding.progress.toggleVisibility(show = false)
                        binding.includeEmptyErrorView.root.toggleVisibility(show = false)
                    }

                    is MultimediaUiState.Success -> {
                        multimediaAdapter.submitList(
                            searchUiState.data?.map { multimediaUiModel ->
                                ItemDataAbstract(
                                    multimediaUiModel
                                )
                            }
                        )
                        binding.progress.toggleVisibility(show = false)
                        binding.includeEmptyErrorView.root.toggleVisibility(show = false)
                        binding.includeGenericErrorView.root.toggleVisibility(show = false)
                        binding.rvContentList.toggleVisibility(true)
                    }
                }
            }
        }
    }

    private fun getAllContent() {
        viewModel.getMultimediaList(TYPE)
        hideAndClear()
        binding.progress.toggleVisibility(show = true)
    }

    private fun networkStatusVisibility() {
        context?.let { context ->
            NetworkStatusHelper(context).observe(viewLifecycleOwner) { networkStatus ->
                when (networkStatus) {
                    NetworkStatus.Unavailable -> {
                        binding.txvOffline.toggleVisibility(show = true)
                    }

                    else -> {
                        binding.txvOffline.toggleVisibility(show = false)
                    }
                }
            }
        }
    }

    private fun showDialog(title: String, body: String, icon: Int) {
        activity?.let {
            var dialog: CustomDialogFragment? = null
            dialog = CustomDialogFragment.Builder(requireContext())
                .setIcon(icon, R.color.colorPrimary)
                .setTitle(title)
                .setMessage(body)
                .setPositiveButton(R.string.accept_label, OnSingleClickListener {
                    dialog?.dismiss()
                })
                .setCancelable(false)
                .create()
            dialog.show(
                it.supportFragmentManager, TAG
            )
        }
        hideAndClear()
    }

    private fun hideAndClear() {
        binding.mainViewSearch.hideKeyboard()
        binding.mainViewSearch.clearFocus()
    }

    private fun onItemSelected(item: MultimediaItemModelUI) {

        resources.getString(
            R.string.description_multimedia,
            item.name,
            item.type.toString()
        )
        showDialog(
            resources.getString(R.string.title_multimedia),
            resources.getString(
                R.string.description_multimedia,
                item.name,
                item.type.toString()
            ),
            R.drawable.ic_serie
        )
    }

}
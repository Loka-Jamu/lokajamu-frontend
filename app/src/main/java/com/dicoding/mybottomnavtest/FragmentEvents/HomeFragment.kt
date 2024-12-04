package com.dicoding.mybottomnavtest.FragmentEvents


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.mybottomnavtest.R
import com.dicoding.mybottomnavtest.adapter.ArticleHomeAdapter
import com.dicoding.mybottomnavtest.adapter.HomeAdapter
import com.dicoding.mybottomnavtest.adapter.HomeRvAdapter
import com.dicoding.mybottomnavtest.adapter.LatestNewsAdapter
import com.dicoding.mybottomnavtest.adapter.NewsAdapter
import com.dicoding.mybottomnavtest.adapter.RecipeAdapter
import com.dicoding.mybottomnavtest.adapter.SpiceAdapter
import com.dicoding.mybottomnavtest.api.ApiClient
import com.dicoding.mybottomnavtest.data.ArticleData
import com.dicoding.mybottomnavtest.data.ListEventsItem
import com.dicoding.mybottomnavtest.data.RecipeData
import com.dicoding.mybottomnavtest.data.SpiceData
import com.dicoding.mybottomnavtest.databinding.FragmentHomeBinding
import com.dicoding.mybottomnavtest.preference.UserPreference
import com.dicoding.mybottomnavtest.preference.dataStore
import com.dicoding.mybottomnavtest.viewmodel.UserViewModel
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {

    private lateinit var eventAdapter: HomeRvAdapter
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var homeAdapter: HomeAdapter
    private lateinit var progressBar: ProgressBar
    private val eventsList = mutableListOf<ListEventsItem>()
    private val filteredEventsList = mutableListOf<ListEventsItem>()
    private val _events = MutableLiveData<List<ListEventsItem>>()
    val events: LiveData<List<ListEventsItem>> = _events
    private val userViewModel: UserViewModel by activityViewModels()

    private lateinit var recipeAdapter: RecipeAdapter
    private lateinit var spiceAdapter: SpiceAdapter
    private lateinit var homeArticelAdapter: ArticleHomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        progressBar = binding.progressBar
        val firstName = userViewModel.firstName.value
        if (firstName != null ) {
            binding.newsName.text = "$firstName"
        } else {

            fetchUserDetails()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvRecipesHome.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        setRecipeAdapter()

        binding.rvSpicesHome.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        setSpiceAdapter()

        binding.rvArticlesHome.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        setArticleAdapter()

//        eventAdapter = HomeRvAdapter(eventsList, this)
//        homeAdapter = HomeAdapter(eventsList, this)
//        homeAdapter = HomeAdapter(filteredEventsList, this)
//        binding.rvRecipesHome.layoutManager = LinearLayoutManager(requireContext())
//        binding.rvRecipesHome.adapter = eventAdapter
//
//        fetchUserDetails()
//
//        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                return false
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                newText?.let {
//                    filterEvents(it)
//                }
//                return true
//            }
//        })
//
//        binding.searchView.setOnClickListener {
//            binding.searchView.isIconified = false
//            binding.searchView.requestFocus()
//        }
    }

    private fun setRecipeAdapter() {
        val dataList: MutableList<RecipeData> = mutableListOf()

        recipeNameDummy().forEachIndexed { index, name ->
            dataList.add(
                RecipeData(
                    index,
                    name,
                    ImageDummy()[index],
                    recipeDescriptionDummy()[index],
                    recipeBenefitDummy()[index]
                )
            )
        }

        recipeAdapter = RecipeAdapter(context, dataList)
        binding.rvRecipesHome.adapter = recipeAdapter
    }

    private fun setSpiceAdapter() {
        val dataList: MutableList<SpiceData> = mutableListOf()

        spiceNameDummy().forEachIndexed { index, name ->
            dataList.add(
                SpiceData(
                    index,
                    name,
                    ImageDummy()[index],
                    spiceDescriptionDummy()[index],
                    spiceBenefitDummy()[index]
                )
            )
        }

        spiceAdapter = SpiceAdapter(context, dataList)
        binding.rvSpicesHome.adapter = spiceAdapter
    }

    private fun setArticleAdapter() {
        val dataList: MutableList<ArticleData> = mutableListOf()

        articleTitleDummy().forEachIndexed { index, title ->
            dataList.add(
                ArticleData(
                    index,
                    ImageDummy()[index],
                    title,
                    articleDateDummy()[index],
                    articleAuthorDummy()[index],
                    articleContentDummy()[index]
                )
            )
        }

        homeArticelAdapter = ArticleHomeAdapter(context, dataList)
        binding.rvArticlesHome.adapter = homeArticelAdapter
    }


    private fun recipeNameDummy(): Array<String> {
        return resources.getStringArray(R.array.RecipeName)
    }
    private fun ImageDummy(): List<Int> {
        return listOf(
            R.drawable.image_article_1,
            R.drawable.image_article_2,
            R.drawable.image_article_3,
            R.drawable.image_article_3,
            R.drawable.image_article_3
        )
    }
    private fun recipeDescriptionDummy(): Array<String> {
        return resources.getStringArray(R.array.RecipeDescription)
    }
    private fun recipeBenefitDummy(): Array<String> {
        return resources.getStringArray(R.array.RecipeBenefit)
    }
    private fun spiceNameDummy(): Array<String> {
        return resources.getStringArray(R.array.SpiceName)
    }
    private fun spiceDescriptionDummy(): Array<String> {
        return resources.getStringArray(R.array.SpiceDescription)
    }
    private fun spiceBenefitDummy(): Array<String> {
        return resources.getStringArray(R.array.SpiceBenefit)
    }
    private fun articleTitleDummy(): Array<String> {
        return resources.getStringArray(R.array.articleTitle)
    }
    private fun articleDateDummy(): Array<String> {
        return resources.getStringArray(R.array.articleDate)
    }
    private fun articleAuthorDummy(): Array<String> {
        return resources.getStringArray(R.array.articleAuthor)
    }
    private fun articleContentDummy(): Array<String> {
        return resources.getStringArray(R.array.articleContent)
    }

    private fun fetchUserDetails() {
        showLoading(true)

        lifecycleScope.launch {
            val userPreference = UserPreference.getInstance(requireContext().dataStore)
            val token = userPreference.getToken()

            if (!token.isNullOrEmpty()) {
                try {
                    val apiService = ApiClient.getApiService()
                    val response = apiService.getUserDetails("Bearer $token")

                    if (response.isSuccessful) {
                        val firstName = response.body()?.data?.firstName
                        if (!firstName.isNullOrEmpty()) {
                            userViewModel.setUserDetailsHome(firstName)
                            binding.newsName.text = "$firstName"
                        } else {
                            showToast("User name is empty.")
                        }
                    } else {
                        showToast("Failed to fetch user details. Status: ${response.code()}")
                    }
                } catch (e: Exception) {
                    showToast("An error occurred: ${e.message}")
                } finally {
                    showLoading(false)
                }
            } else {
                showToast("Token is missing.")
                showLoading(false)
            }
        }
    }

    private fun filterEvents(query: String) {
        val filteredList = if (query.isEmpty()) {
            eventsList
        } else {
            eventsList.filter {
                it.name.contains(query, ignoreCase = true) || it.description.contains(query, ignoreCase = true)
            }
        }

        homeAdapter.updateData(filteredList)

//        if (filteredList.isEmpty()) {
//            binding.noDataMessage.visibility = View.VISIBLE
//        } else {
//            binding.noDataMessage.visibility = View.GONE
//        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


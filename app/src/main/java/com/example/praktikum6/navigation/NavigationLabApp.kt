package com.example.praktikum6.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.praktikum6.data.remote.RetrofitClient
import com.example.praktikum6.data.remote.StudentRemoteDataSource
import com.example.praktikum6.data.repository.DefaultStudentRepository
import com.example.praktikum6.ui.detail.DetailViewModel
import com.example.praktikum6.ui.home.HomeViewModel
import com.example.praktikum6.ui.screen.AboutScreen
import com.example.praktikum6.ui.screen.DetailScreen
import com.example.praktikum6.ui.screen.HomeScreen
import com.example.praktikum6.ui.screen.ProfileScreen

@Composable
fun NavigationLabApp() {
    val remoteDataSource = remember {
        StudentRemoteDataSource(RetrofitClient.apiService)
    }
    val repository = remember {
        DefaultStudentRepository(remoteDataSource)
    }
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.HOME
    ) {
        composable(Routes.HOME) {
            val homeViewModel: HomeViewModel = viewModel(
                factory = HomeViewModel.factory(repository)
            )
            HomeScreen(
                uiState = homeViewModel.uiState,
                onStudentClick = { id -> navController.navigate(Routes.detail(id)) },
                onRetry = homeViewModel::loadStudents,
                onSearchQueryChange = homeViewModel::onSearchQueryChange,
                onSearchClick = homeViewModel::onSearchClick,
                onOpenProfile = { navController.navigate(Routes.PROFILE) },
                onOpenAbout = { navController.navigate(Routes.ABOUT) }
            )
        }

        composable(
            route = Routes.DETAIL,
            arguments = listOf(
                navArgument("studentId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val studentId = backStackEntry.arguments?.getInt("studentId") ?: -1
            val detailViewModel: DetailViewModel = viewModel(
                factory = DetailViewModel.factory(repository)
            )
            LaunchedEffect(studentId) {
                detailViewModel.loadStudent(studentId)
            }
            DetailScreen(
                uiState = detailViewModel.uiState,
                onBack = { navController.popBackStack() }
            )
        }

        composable(Routes.PROFILE) {
            ProfileScreen(onBack = { navController.popBackStack() })
        }
        composable(Routes.ABOUT) {
            AboutScreen(onBack = { navController.popBackStack() })
        }
    }
}

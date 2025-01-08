pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "SpotifyCompose"
include(":app")
include(":feature")
include(":feature:player")
include(":core:android")
include(":core:model")
include(":core:repository")
include(":core:usecase")
include(":core:database")
include(":core:converter")

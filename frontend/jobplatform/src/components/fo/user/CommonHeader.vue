<template>
    <header id="header" class="header-effect-shrink " data-plugin-options="{'stickyEnabled': true, 'stickyEffect': 'shrink', 'stickyEnableOnBoxed': true, 'stickyEnableOnMobile': false, 'stickyChangeLogo': true, 'stickyStartAt': 30, 'stickyHeaderContainerHeight': 70}" style="height: 131px;">
        <div class="header-body header-body-bottom-border border-top-0 bg-color-grey" style="top: 0px;">
            <div class="header-container container" style="height: 70px; min-height: 0px;">
                <div class="header-row">
                    <div class="header-column">
                        <div class="header-row">
                            <h1 class="header-logo" style="width: 100px; height: 48px;">
                                <a href="index.html">
                                    <img alt="Porto" width="100" height="48" src="img/logo-default-slim.png" style="top: 0px; width: 100px; height: 48px;">
                                    <span class="hide-text">Porto - Demo Blog 2</span>
                                </a>
                            </h1>
                        </div>
                    </div>
                    <div class="header-column justify-content-end">
                        <div class="header-row">
                            <div class="header-nav header-nav-line header-nav-bottom-line header-nav-bottom-line-effect-1">
                                <div class="header-nav-main header-nav-main-square header-nav-main-font-lg header-nav-main-square header-nav-main-dropdown-no-borders header-nav-main-effect-2 header-nav-main-sub-effect-1">
                                    <nav class="collapse">
                                        <ul class="nav nav-pills" id="mainNav">
                                            <li class="dropdown">
                                                <a class="dropdown-item dropdown-toggle active current-page-active" href="index.html">
                                                    Home
                                                <i class="fas fa-chevron-down"></i></a>
                                                <ul class="dropdown-menu">
                                                    <li>
                                                        <a class="dropdown-item" href="index.html">
                                                            Landing Page
                                                        </a>
                                                    </li>
                                                </ul>
                                            </li>
                                            <li class="dropdown dropdown-mega">
                                                <a class="dropdown-item dropdown-toggle" href="elements.html">
                                                    Elements
                                                <i class="fas fa-chevron-down"></i></a>
                                            </li>
                                            <li class="dropdown">
                                                <a class="dropdown-item dropdown-toggle" href="#">
                                                    Features
                                                <i class="fas fa-chevron-down"></i></a>
                                            </li>
                                            <li class="dropdown">
                                                <a class="dropdown-item dropdown-toggle" href="#">
                                                    Pages
                                                <i class="fas fa-chevron-down"></i></a>
                                            </li>
                                            <li class="dropdown">
                                                <a class="dropdown-item dropdown-toggle" href="#">
                                                    Portfolio
                                                <i class="fas fa-chevron-down"></i></a>
                                            </li>
                                            <li class="dropdown">
                                                <a class="dropdown-item dropdown-toggle" href="#">
                                                    Blog
                                                <i class="fas fa-chevron-down"></i></a>
                                            </li>
                                            <li class="dropdown dropdown-reverse">
                                                <a class="dropdown-item dropdown-toggle" href="#">
                                                    Shop
                                                <i class="fas fa-chevron-down"></i></a>
                                            </li>
                                        </ul>
                                    </nav>
                                </div>
                                <button class="btn header-btn-collapse-nav" data-bs-toggle="collapse" data-bs-target=".header-nav-main nav">
                                    <i class="fas fa-bars"></i>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </header>
</template>
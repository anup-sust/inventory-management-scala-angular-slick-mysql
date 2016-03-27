/**
 * Created by anupam.deb on 8/16/2015.
 */

'use strict';

// this doesn't seem to be working:
// var invApp.filters = angular.module('invApp.filters', []);

var invMgmt = angular.module('invMgmt', ['ui.router', 'ngResource', 'ui.select2', 'ui.bootstrap', 'restangular']).
    config(['$stateProvider', '$locationProvider', function ($stateProvider, $locationProvider) {
        $stateProvider
            .state('Home', {
                url: "/",
                templateUrl: "/assets/partials/home.html",
                resolve: {
                    load: ['$rootScope', function ($rootScope) {
                        return $rootScope.account.$promise;
                    }]
                }
            }).state('SignIn', {
                url: "/signin",
                templateUrl: "/assets/partials/sign-in.html",
                controller: 'SignInCtrl',
                resolve: {
                    load: ['$rootScope', function ($rootScope) {
                        return $rootScope.account.$promise;
                    }]
                }
            }).state('SignUp', {
                url: "/signup",
                templateUrl: "/assets/partials/sign-up.html",
                controller: 'SignUpCtrl',
                resolve: {
                    load: ['$rootScope', function ($rootScope) {
                        return $rootScope.account.$promise;
                    }]
                }
            }).state('User', {
                url: "/users",
                templateUrl: "/assets/partials/user-list.html",
                controller: 'UserCtrl',
                resolve: {
                    load: ['$rootScope', function ($rootScope) {
                        return $rootScope.account.$promise;
                    }]
                }
            }).state('Product', {
                url: "/products",
                templateUrl: "/assets/partials/product-root.html",
                abstract: true,
                controller: 'ProductRootCtrl',
                resolve:{load:['$rootScope', function ($rootScope) {
                    return $rootScope.account.$promise;
                }]}

            }).state('Product.default', {
                url: "",
                templateUrl: "/assets/partials/products.html",
                controller: 'ProductsCtrl'
            }).state('Product.add', {
                url: "/new",
                templateUrl: "/assets/partials/product-add.html",
                controller: 'ProductAddCtrl'
            }).state('Product.details', {
                url: "/{id}",
                templateUrl: "/assets/partials/product-details.html",
                controller: 'ProductDetailCtrl'
            }).state('ProductStatus', {
                url: "/product-status",
                templateUrl: "/assets/partials/product-status-root.html",
                abstract: true,
                controller: 'ProductStatusRootCtrl',
                resolve:{load:['$rootScope', function ($rootScope) {
                    return $rootScope.account.$promise;
                }]}

            }).state('ProductStatus.default', {
                url: "",
                templateUrl: "/assets/partials/productStatus.html",
                controller: 'ProductStatusCtrl'
            }).state('ProductStatus.add', {
                url: "/new",
                templateUrl: "/assets/partials/product-status-add.html",
                controller: 'ProductStatusAddCtrl'
            }).state('ProductStatus.details', {
                url: "/{id}",
                templateUrl: "/assets/partials/product-status-details.html",
                controller: 'ProductStatusDetailCtrl'
            }).state('Supplier', {
                url: "/suppliers",
                templateUrl: "/assets/partials/supplier-root.html",
                abstract: true,
                controller: 'SupplierRootCtrl',
                resolve:{load:['$rootScope', function ($rootScope) {
                    return $rootScope.account.$promise;
                }]}
            }).state('Supplier.default', {
                url: "",
                templateUrl: "/assets/partials/suppliers.html",
                controller: 'SupplierCtrl'
            }).state('Supplier.add', {
                url: "/new",
                templateUrl: "/assets/partials/supplier-add.html",
                controller: 'SupplierAddCtrl'
            }).state('Supplier.id', {
                url: "/{id}",
                templateUrl: "/assets/partials/supplier-details.html",
                controller: 'SupplierDetailCtrl'
            }).state('Category', {
                url: "/categories",
                templateUrl: "/assets/partials/category-root.html",
                abstract: true,
                controller: 'CategoryRootCtrl',
                resolve:{load:['$rootScope', function ($rootScope) {
                    return $rootScope.account.$promise;
                }]}
            }).state('Category.default', {
                url: "",
                templateUrl: "/assets/partials/categories.html",
                controller: 'CategoriesCtrl'
            }).state('Category.add', {
                url: "/new",
                templateUrl: "/assets/partials/categories-add.html",
                controller: 'CategoryAddCtrl'
            }).state('Category.id', {
                url: "/{id}",
                templateUrl: "/assets/partials/categories-details.html",
                controller: 'CategoryDetailCtrl'
            }).state('Request', {
                url: "/requests",
                templateUrl: "/assets/partials/product-requests.html",
                controller: 'ProductRequestsCtrl',
                resolve:{load:['$rootScope', function ($rootScope) {
                    return $rootScope.account.$promise;
                }]}
            });


        $locationProvider.html5Mode(true).hashPrefix('!');
    }]);


invMgmt.run(['$rootScope', 'Authenticate', '$state', function ($rootScope, Authenticate, $state) {

    $rootScope._ = _;
    $rootScope.$state = $state;
    $rootScope.setRole = function () {
        if($rootScope.account.banned) {
            alert("You are banned from the system.");
            return;
        }
        if ($rootScope.account.role == "UnVerified") $rootScope.UnVerified = true;
        else if ($rootScope.account.role == "User") $rootScope.NormalUser = true;
        else if ($rootScope.account.role == "Admin") $rootScope.Admin = true;
        else if ($rootScope.account.role == "Supar Admin") $rootScope.SuparAdmin = true;

    };
    $rootScope.account = Authenticate.POST({dummy: "xyz"}, function (data) {
    });

    $rootScope.account.$promise.then(function () {
        $rootScope.setRole();
    });

}]);

invMgmt.directive('onEnter', [function () {
    return function (scope, elm, attr) {
        elm.bind('keypress', function (e) {
            if (e.keyCode === 13) {
                scope.$apply(attr.onEnter);
            }
        });
    };
}]);

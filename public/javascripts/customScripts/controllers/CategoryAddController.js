/**
 * Created by Anup_sust on 8/22/2015.
 */

invMgmt.controller('CategoryAddCtrl',['$scope', '$resource', 'Product', '$stateParams', '$location', 'Supplier', 'Category', function ($scope, $resource, Product, $stateParams, $location, Supplier, Category) {

    $scope.selectedCategory = {};

    $scope.add = function () {
        Category.add($scope.selectedCategory, function () {
            $location.path('/categories');
        }, function () {

        });
    };





}]);
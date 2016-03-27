/**
 * Created by Anup_sust on 8/22/2015.
 */

invMgmt.controller('ProductAddCtrl',['$scope', '$resource', 'Product', '$stateParams', '$location', function ($scope, $resource, Product, $stateParams, $location) {

    $scope.selectedProduct = {};

    $scope.add = function () {

        Product.add($scope.selectedProduct, function () {
            $location.path('/products');
        }, function () {

        });

        //$scope.selectedProduct.add(function () {
        //    $location.path('/products');
        //}, function () {
        //
        //});
    };





}]);
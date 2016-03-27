/**
 * Created by Anup_sust on 8/22/2015.
 */

invMgmt.controller('ProductStatusAddCtrl',['$scope', '$resource', 'Product', '$stateParams', '$location', 'Productstatus', function ($scope, $resource, Product, $stateParams, $location, Productstatus) {

    $scope.selectedProductstatus = {};

    $scope.add = function () {

        Productstatus.add($scope.selectedProductstatus, function () {
            $location.path('/product-status');
        }, function () {

        });

        //$scope.selectedProduct.add(function () {
        //    $location.path('/products');
        //}, function () {
        //
        //});
    };





}]);
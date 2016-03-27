/**
 * Created by Anup_sust on 8/22/2015.
 */

invMgmt.controller('SupplierAddCtrl',['$scope', '$resource', 'Product', '$stateParams', '$location', 'Supplier', function ($scope, $resource, Product, $stateParams, $location, Supplier) {

    $scope.selectedSupplier = {};

    $scope.add = function () {
        Supplier.add($scope.selectedSupplier, function () {
            $location.path('/suppliers');
        }, function () {

        });
    };





}]);
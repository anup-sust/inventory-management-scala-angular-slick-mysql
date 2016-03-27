/**
 * Created by Anup_sust on 8/22/2015.
 */

invMgmt.controller('ProductStatusRootCtrl', ['$scope', '$resource', 'Product', '$location', 'Supplier', 'Category', 'Productstatus', function ($scope, $resource, Product, $location, Supplier, Category, Productstatus) {



    if(!$scope.SuparAdmin&&!$scope.Admin&&!$scope.NormalUser) $location.path('/');


}]);
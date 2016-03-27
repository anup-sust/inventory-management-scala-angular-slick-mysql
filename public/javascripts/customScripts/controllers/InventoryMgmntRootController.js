/**
 * Created by Anup_sust on 8/22/2015.
 */

invMgmt.controller('InventoryMgmntRootCtrl', ['$scope', '$resource', 'Product', '$stateParams', 'Supplier', 'Category', 'Productstatus', 'Authenticate', 'Restangular', '$http', function ($scope, $resource, Product, $stateParams, Supplier, Category, Productstatus, Authenticate, Restangular, $http) {

    $scope.inventoryMgmnt = {};

    function loadImage(brand, extension) {
        console.log("loadImage");
        var param = {};
        param['searchType'] = 'image';
        param['start'] = 1;
        param['num'] = 1;
        param['cx'] = "010583044915937555780:wrcbodtbyo8";
        param['key'] = "AIzaSyBxoHAvKN_Sk5M2Do8JFPE-JfVOCq1nY-c";
        param['q'] = brand + ' logo ' + extension;
        return $http.get("https://www.googleapis.com/customsearch/v1", {
            params: param
        });
    }

    loadImage("hp", "jpg").then(function (data) {
        console.log(data.data.items);
    });


    //var rest = Restangular.withConfig(function (RestangularConfigurer) {
    //    RestangularConfigurer.setBaseUrl('https://api.caresoftware.ch');
    //});
    //
    //var data = {
    //    Username: "selise1@selise.ch",
    //    Password: "PassW0rd!",
    //    grant_type: "password"
    //};
    //
    //rest.all("token").customPOST(
    //    $.param(data),
    //    undefined, // put your path here
    //    undefined, // params here, e.g. {format: "json"}
    //    {'Content-Type': "application/x-www-form-urlencoded; charset=UTF-8"}
    //);

        //$scope.account = Authenticate.POST({dummy:"xyz"}, function (data) {
        //    console.log($scope.account);
        //});

        //Supplier.GET(function (data) {
        //    $scope.inventoryMgmnt.suppliers = data.records;
        //});
        //
        //Category.GET(function (data) {
        //    $scope.inventoryMgmnt.categories = data.records;
        //});
        //
        //Productstatus.GET(function (data) {
        //    $scope.inventoryMgmnt.productstatus = data.records;
        //});

        $scope.goToDrupalSite = function (event) {
        if (event.target.pathname != undefined && event.target.pathname != '/undefined' && event.target.pathname != '') {
            if (event.ctrlKey) window.open(event.target.href, '_blank');
            else window.location = event.target.href;
        }
        if (event) event.preventDefault();
    };

}]);
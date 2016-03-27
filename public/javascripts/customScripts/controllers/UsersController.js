/**
 * Created by Anup_sust on 8/17/2015.
 */

invMgmt.controller('UserCtrl',['$scope', '$resource','Authenticate', 'User', '$location', function ($scope, $resource, Authenticate, User, $location) {


    if(!$scope.SuparAdmin&&!$scope.Admin) $location.path('/');

    $scope.userList = {};

    User.GETALL(function (data) {
        $scope.userList.users = data.records;
    });

    User.ROLES(function (data) {
        $scope.userList.roles = data.records;
    });

    $scope.update = function (user) {
        if(user.userRole) user.userRole = parseInt(user.userRole);
        User.PUT(user, function success() {

        }, function failure() {

        });
    } ;

}]);

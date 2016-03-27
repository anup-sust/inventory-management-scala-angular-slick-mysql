/**
 * Created by Anup_sust on 8/17/2015.
 */

invMgmt.controller('SignUpCtrl',['$scope', '$resource','$location', '$rootScope', 'User', function ($scope, $resource, $location, $rootScope, User) {



    //if($scope.account.name) $location.path('/');
    //
    //
    //$scope.signIn = {};
    //
    //
    //var Authenticate = $resource('/api/login', {}, {
    //    POST: {method: 'POST'}
    //});
    //
    //$scope.login = function (user,pass) {
    //    if($scope.loginform.$invalid) return;
    //    Authenticate.POST({userId: user, password: pass}, function (response) {
    //        $rootScope.account = response;
    //        if($rootScope.account.name) $location.path('/');
    //        else $scope.signIn.invalidSignIn = true;
    //    });
    //};

    var Authenticate = $resource('/api/login', {}, {
        POST: {method: 'POST'}
    });

    $scope.signUp = {};
    $scope.signUpAct = function () {
        if($scope.signUpform.$invalid) return;

        User.SIGNUP($scope.signUp, function (response) {
            if(response.name) {
                $rootScope.account = response;
                $rootScope.setRole();
                if ($rootScope.account.name) $location.path('/');
            }
            else{
                $scope.signUp.invalidSignIn= true;
            }
        });
    };
}]);
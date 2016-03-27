/**
 * Created by Anup_sust on 8/17/2015.
 */
invMgmt.factory('User', ['$resource', function ($resource) {

    var Users = $resource('/api/user/:cmd/:id', {}, {
        POST: {method: 'POST'},
        DELETE: {method: 'DELETE', params: {id: '@id'}},
        GETALL: {method: 'GET'},
        GETALLDETAIL: {method: 'GET', params : {cmd: 'detail'}},
        ROLES: {method: 'GET', params : {cmd: 'roles'}},
        GET: {method: 'GET'},
        PUT: {method: 'PUT'},
        SIGNUP: {method: 'POST', params : {cmd: 'signup'}}
    });
    Users.prototype.update = function (success, error) {
        Supplier.PUT(this, success, error);
    };

    Users.add = function (obj, success, error) {
        Supplier.POST(obj, success, error);
    };

    return Users;

}]);
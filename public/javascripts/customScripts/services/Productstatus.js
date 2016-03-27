/**
 * Created by Anup_sust on 8/17/2015.
 */
invMgmt.factory('Productstatus', ['$resource', function ($resource) {

    var Productstatus = $resource('/api/productstatus/:id/:detail/:did', {}, {
        GET: {method: 'GET', params: {id: '@id'}},
        DELETE: {method: 'DELETE', params: {id: '@id'}},
        GET_DETAILS: {method: 'GET', params: {detail: 'details', id: '@id'}},
        GET: {method: 'GET'},
        POST: {method: 'POST'},
        PUT: {method: 'PUT'}
    });

    Productstatus.prototype.update = function (success, error) {
        Productstatus.PUT(this, success, error);
    };

    Productstatus.add = function (obj, success, error) {
        Productstatus.POST(obj, success, error);
    };

    return Productstatus;

}]);
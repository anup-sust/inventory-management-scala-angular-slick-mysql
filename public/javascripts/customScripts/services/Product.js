/**
 * Created by Anup_sust on 8/17/2015.
 */
invMgmt.factory('Product', ['$resource', function ($resource) {

    var Product = $resource('/api/product/:cmd/:id/:detail/:action/:pid', {}, {
        GET: {method: 'GET', params: {id: '@id'}},
        DELETE: {method: 'DELETE', params: {id: '@id'}},
        PUT: {method: 'PUT', params: {}},
        GET_DETAILS: {method: 'GET', params: {detail: 'details', id: '@id'}},
        GET: {method: 'GET'},
        POST: {method: 'POST'},
        GET_REQUEST_DETAIL: {method: 'GET', params: {cmd: 'request', detail: 'details'}},
        UPDATE_REQUEST: {method: 'PUT', params: {cmd: 'request'}},
        DELETE_REQUEST: {method: 'DELETE', params: {cmd: 'request', id: '@id'}},
        GET_REQUEST_DETAIL_FOR_PRODUCT: {
            method: 'GET',
            params: {cmd: 'request', detail: 'details', action: 'product', pid: '@pid'}
        },
        ADD_REQUEST: {method: 'POST', params: {cmd: 'request'}}
    });

    Product.prototype.update = function (success, error) {
        if (this.supplierId) {
            this.supplierId = parseInt(this.supplierId)
        } else {
            this.supplierId = 0;
        }
        if (this.categoryId) {
            this.categoryId = parseInt(this.categoryId)
        } else {
            this.categoryId = 0;
        }
        if (this.ProductStatusID) {
            this.ProductStatusID = parseInt(this.ProductStatusID)
        } else {
            this.ProductStatusID = 0;
        }
        if (this.quantityPerUnit) this.quantityPerUnit = parseInt(this.quantityPerUnit);
        if (this.unitPrice)  this.unitPrice = parseInt(this.unitPrice);
        if (this.unitsInStock)  this.unitsInStock = parseInt(this.unitsInStock);
        if (this.unitsOnOrder) this.unitsOnOrder = parseInt(this.unitsOnOrder);
        Product.PUT(this, success, error);
    };

    Product.add = function (obj, success, error) {
        if (obj.supplierId) obj.supplierId = parseInt(obj.supplierId); else obj.supplierId = 0;
        if (obj.categoryId) obj.categoryId = parseInt(obj.categoryId); else obj.categoryId = 0;
        if (obj.ProductStatusID) obj.ProductStatusID = parseInt(obj.ProductStatusID); else obj.ProductStatusID = 0;
        if (obj.quantityPerUnit) obj.quantityPerUnit = parseInt(obj.quantityPerUnit);
        if (obj.unitPrice)  obj.unitPrice = parseInt(obj.unitPrice);
        if (obj.unitsInStock)  obj.unitsInStock = parseInt(obj.unitsInStock);
        if (obj.unitsOnOrder) obj.unitsOnOrder = parseInt(obj.unitsOnOrder);
        obj.created = new Date().getTime() + "";
        Product.POST(obj, success, error);
    };

    return Product;

}]);
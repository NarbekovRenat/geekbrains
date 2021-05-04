angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8080/market';

    $scope.init = function () {
        $http.get(contextPath + '/api/v1/products')
            .then(function (response) {
                $scope.products = response.data;
            });
        $scope.countProductsInCart();
    };

    $scope.initBasket = function () {
        $http.get(contextPath + '/api/v1/cart')
            .then(function (response) {
                $scope.basket = response.data;
            });
        $scope.countProductsInCart();
    };

    $scope.createNewProduct = function () {
        $http.post(contextPath + '/api/v1/products', $scope.newProduct)
            .then(function successCallback(response) {
                $scope.init();
                $scope.newProduct = null;
            }, function errorCallback(response) {
                console.log(response.data);
                alert('Error: ' + response.data.messages);
            });
    };

    $scope.deleteProduct = function (id) {
        $http.delete(contextPath + '/api/v1/products/' + id)
            .then(function successCallback(response) {
                $scope.init();
            }, function errorCallback(response) {
                console.log(response.data);
                alert('Error: ' + response.data.messages);
            });
    };

    //Cart functions
    $scope.countProductsInCart = function () {
        $http.get(contextPath + '/api/v1/cart/count')
            .then(function (response) {
                $scope.cartsize = response.data;
            });
    };

    $scope.addProductToCart = function (product) {
        $http.put(contextPath + '/api/v1/cart', product)
            .then(function successCallback(response) {
                $scope.init();
            }, function errorCallback(response) {
                console.log(response.data);
                alert('Error: ' + response.data.messages);
            });
    };


    $scope.deleteProduct = function (id) {
        $http.delete(contextPath + '/api/v1/cart/' + id)
            .then(function successCallback(response) {
                $scope.initBasket();
            }, function errorCallback(response) {
                console.log(response.data);
                alert('Error: ' + response.data.messages);
            });
    };

    $scope.wipeBasket = function () {
        $http.get(contextPath + '/api/v1/cart/wipe')
            .then(function (response) {
                $scope.initBasket();
            });
    };

    $scope.init();
    $scope.initBasket();
});
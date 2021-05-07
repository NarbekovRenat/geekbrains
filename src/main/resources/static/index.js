angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8080/market';

    $scope.loadProductsPage = function (page) {
        $http({
            url: contextPath + '/api/v1/products',
            method: 'GET',
            params: {
                p: page
            }
        }).then(function (response) {
            $scope.productsPage = response.data;
            $scope.generatePaginationArray(1, $scope.productsPage.totalPages);
            $scope.countProductsInCart();
        });
    };

    $scope.createNewProduct = function () {
        $http.post(contextPath + '/api/v1/products', $scope.newProduct)
            .then(function successCallback(response) {
                $scope.loadProductsPage(1);
                $scope.newProduct = null;
            }, function errorCallback(response) {
                console.log(response.data);
                alert('Error: ' + response.data.messages);
            });
    };

    $scope.deleteProduct = function (id) {
        $http.delete(contextPath + '/api/v1/products/' + id)
            .then(function successCallback(response) {
                $scope.loadProductsPage(1);
            }, function errorCallback(response) {
                console.log(response.data);
                alert('Error: ' + response.data.messages);
            });
    };

    // utils
    $scope.generatePaginationArray = function (startPage, endPage) {
        let arr = [];
        for (let i = startPage; i < endPage + 1; i++) {
            arr.push(i);
        }
        $scope.paginationArray = arr;
    };

    //Cart functions
    $scope.initBasket = function () {
        $http.get(contextPath + '/api/v1/cart')
            .then(function successCallback(response) {
                console.log(response);
                $scope.basket = response.data;
            }, function errorCallback(response) {
                console.log(response.data);
                alert('Error: ' + response.data.messages);
            });
        $scope.countProductsInCart();
    };

    $scope.countProductsInCart = function () {
        $http.get(contextPath + '/api/v1/cart/count')
            .then(function (response) {
                $scope.cartsize = response.data;
            });
    };

    $scope.addProductToCart = function (id) {
        $http({
            url: contextPath + '/api/v1/cart',
            method: 'PUT',
            params: {
                id: id
            }
        }).then(function successCallback(response) {
            $scope.loadProductsPage(1);
        }, function errorCallback(response) {
            console.log(response.data);
            alert('Error: ' + response.data.messages);
        });
    };

    $scope.deleteProductFromBasket = function (id) {
        $http({
            url: contextPath + '/api/v1/cart/' + id,
            method: 'DELETE'
        }).then(function () {
            $scope.initBasket();
        });
    };

    $scope.wipeBasket = function () {
        $http.get(contextPath + '/api/v1/cart/wipe')
            .then(function () {
                $scope.initBasket();
            });
    };

    $scope.loadProductsPage(1);
    $scope.countProductsInCart();
});
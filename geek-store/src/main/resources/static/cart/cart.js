angular.module('front').controller('cartController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market/api/v1';


    $scope.loadCartProducts = function () {
        $http.get(contextPath + '/carts')
            .then(function (response) {
                $scope.cartPage = response.data;
            });

    }

    $scope.deleteScore = function (cartId) {
        $http.get(contextPath + '/carts/delete/' + cartId)
            .then(function (response) {
                $scope.loadCartProducts();

            });
    }
    $scope.loadCartProducts();

});
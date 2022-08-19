angular.module('front').controller('createProductController', function ($scope, $http, $routeParams, $location) {
    const contextPath = 'http://localhost:8189/market/api/v1';

    $scope.createProduct = function () {
        if ($scope.newProduct == null) {
            alert("Форма не заполнена");
            return;
        }
        $http.post(contextPath + '/products', $scope.newProduct)
            .then(function successCallback(response) {
                $scope.newProduct = null;
                alert("Продукт успешно добавлен");
                $location.path('/store');

            }, function failCallback(response) {
                alert(response.data.message);
                $location.path('/store');
            });
    }

});


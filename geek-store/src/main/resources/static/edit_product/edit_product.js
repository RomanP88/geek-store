angular.module('front').controller('editProductController', function ($scope, $http, $routeParams, $location) {
    const contextPath = 'http://localhost:8189/market/api/v1';


    $scope.productForUpdateList = function () {
        $http.get(contextPath + '/products/' + $routeParams.productId)

            .then(function successCallback(response) {
                $scope.new_update = response.data;
            }, function failCallback(response) {
                alert(response.data.message);
                $location.path('/store');

            });
    }


    $scope.updateProduct = function () {
        $http.put(contextPath + '/products', $scope.new_update)

            .then(function successCallback(response) {
                $scope.new_update = null;
                alert("Продукт успешно обновлен");
                $location.path('/store');

            }, function failCallback(response) {
                alert(response.data.message);

            });
    }

    $scope.productForUpdateList();
});


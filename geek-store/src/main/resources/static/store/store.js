angular.module('front').controller('storeController', function ($scope, $http, $location) {
    const contextPath = 'http://localhost:8189/market/api/v1/';

    let currentPageIndex = 1;

    $scope.loadProducts = function (pageIndex = 1) {
        currentPageIndex = pageIndex;
        $http({
            url: contextPath + 'products',
            method: 'GET',
            params: {
                p: pageIndex
            }
        }).then(function (response) {
            $scope.ProductsPage = response.data;
            $scope.paginationArray = $scope.generatePageIndex(1, $scope.ProductsPage.totalPages);
        });
    }


    $scope.deleteScore = function (productId) {
        $http.delete(contextPath + 'products/delete/' + productId)
            .then(function (response) {
                $scope.loadProducts(currentPageIndex);
            });
    }

    $scope.generatePageIndex = function (startPage, lastPage) {
        let arr = [];
        for (let i = startPage; i < lastPage + 1; i++) {
            arr.push(i);
        }
        return arr;
    }

    $scope.nextPage = function () {
        currentPageIndex++;
        if (currentPageIndex > $scope.ProductsPage.totalPages) {
            currentPageIndex = $scope.ProductsPage.totalPages;
        }
        $scope.loadProducts(currentPageIndex);
    }

    $scope.prevPage = function () {
        currentPageIndex--;
        if (currentPageIndex < 1) {
            currentPageIndex = $scope.ProductsPage.totalPages;
        }
        $scope.loadProducts(currentPageIndex);
    }

    $scope.loadProducts(currentPageIndex);


    $scope.productToList = function (productId){
        $location.path('edit_product/' + productId);
    }


    $scope.adCart = function (id){
        $http.post(contextPath + 'carts/' + id);
        alert("Товар добавлен в корзину")


    }


});


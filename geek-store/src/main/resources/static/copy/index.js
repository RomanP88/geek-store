angular.module('front', []).controller('appController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market/api/v1';

    let currentPageIndex = 1;

    $scope.loadProducts = function (pageIndex = 1) {
        currentPageIndex = pageIndex;
        $http({
            url:contextPath + '/products',
            method: 'GET',
            params: {
                p: pageIndex
            }
        }).then(function (response) {
            $scope.ProductsPage = response.data;
            $scope.paginationArray = $scope.generatePageIndex(1,$scope.ProductsPage.totalPages);
        });
    }


    $scope.deleteScore = function (productId) {
        $http.delete(contextPath + '/products/delete/' + productId)
            .then(function (response) {
                $scope.loadProducts(currentPageIndex);
            });
    }


    $scope.createProduct = function (){
        $http.post(contextPath + '/products', $scope.newProduct)
            .then(function successCallback (response){
                $scope.loadProducts(currentPageIndex);
                $scope.newProduct = null;

            }, function failCallback (responce) {
                alert(responce.data.message)
            });
    }

    $scope.generatePageIndex = function (startPage, lastPage){
        let arr = [];
        for (let i = startPage; i < lastPage + 1; i++) {
            arr.push(i);
        }
        return arr;
    }


    $scope.nextPage = function (){
        currentPageIndex++;
        if(currentPageIndex > $scope.ProductsPage.totalPages){
            currentPageIndex = $scope.ProductsPage.totalPages;
        }
        $scope.loadProducts(currentPageIndex);
    }

    $scope.prevPage = function (){
        currentPageIndex--;
        if(currentPageIndex < 1){
            currentPageIndex = $scope.ProductsPage.totalPages;
        }
        $scope.loadProducts(currentPageIndex);
    }

    $scope.loadProducts(currentPageIndex);



    $scope.productForUpdateList = function (productId){
        $http.get(contextPath + '/products/'+ productId)
            .then(function successCallback (response){

                $scope.new_update = response.data;

            }, function failCallback (response) {
                alert(response.data.message)
            })
    }


    $scope.updateProduct = function (){
        $http.put(contextPath + '/products', $scope.new_update)
            .then(function successCallback (response){
                $scope.loadProducts(currentPageIndex);
                $scope.new_update = null;

            }, function failCallback (response) {
                alert(responce.data.message)
            });
    }
});

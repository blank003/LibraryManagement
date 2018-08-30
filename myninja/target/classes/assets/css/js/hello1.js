angular.module('demo', [])
    .controller('Hello', function($scope, $http) {
        $http.get('http://localhost:8080/showAll').
        then(function(response) {
            //  console.log(response);
            $scope.result = response.data;
            console.log($scope.result);

        });
        $scope.results='YES';
        $scope.submitResult = function(results,res) {
            console.log(results)
            console.log(res)
            //alert(results)
            $http.get("http://localhost:8080/deleteBook/"+ results +"/" +res.id)
                .success(function (data) {

                    // alert("Data: " + data + "\nStatus: " + status);
                    res.bookAvail = results;
                })

        };
    });
//Call api and perform delete
function deleteRow(row) {
    console.log(typeof (row.name));
    var i = row.parentNode.parentNode.rowIndex;
    document.getElementById("table1").deleteRow(i);
    $.get("http://localhost:8080/deleteUser/"+ row.name, function(data, status){
        alert("Data: " + data + "\nStatus: " + status);
    });
 }
//Radio Button



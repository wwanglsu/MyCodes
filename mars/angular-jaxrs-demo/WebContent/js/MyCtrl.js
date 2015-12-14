//AngularJS Controller.

app.controller('MyCtrl', ['$scope', '$state', 'Page',
  function MyCtrl($scope, $state, Page) {

	$scope.$Page = Page;
	
    $scope.home = function() {
      $state.go('home').then(function() {
    	  $('body').toggleClass('pushed-right');
          $('#navbar').toggleClass('in');
          Page.setTitle('Home');
      });
    };

    $scope.about = function() {
      $state.go('about').then(function() {
    	  $('body').toggleClass('pushed-right');
          $('#navbar').toggleClass('in');
          Page.setTitle('About');
      });
    };
    
    $scope.converters = function() {
        $state.go('converters').then(function() {
      	  $('body').toggleClass('pushed-right');
            $('#navbar').toggleClass('in');
            Page.setTitle('Converters');
        });
      };
    
    $scope.contact = function() {
        $state.go('contact').then(function() {
      	  $('body').toggleClass('pushed-right');
            $('#navbar').toggleClass('in');
            Page.setTitle('Contact');
        });
    };
      
    $scope.search = function() {
      $state.go('search').then(function() {
    	  $('body').toggleClass('pushed-right');
          $('#navbar').toggleClass('in');
          Page.setTitle('Search');
      });
    };

    $scope.login = function() {
      $state.go('login').then(function() {
    	  $('body').toggleClass('pushed-right');
          $('#navbar').toggleClass('in');
          Page.setTitle('Log In');
      });      
    };
  }
]);

app.controller('LogInController', ['$scope', '$http',
  function LogInController($scope, $http) {
	
	$scope.validated = false;
	
	$scope.validateUser = function(){	
		
		$http.get(wsHostUrl + "webapi/validateuser/"+$scope.username+"/"+$scope.password)
	    .then(function(response) {
	    	$scope.result = JSON.stringify(response.data);
	    	$scope.validated = response.data;
	    	console.log(response.data);
	    	//alert("test");
    	});
	}
	
  }
]);

app.controller('getWeatherController', function($scope, $http) {
	
	$scope.checked = false;
	
	$scope.calculate = function(){	
		var num = $scope.inputNum;
		if(num == undefined || num == ""){
			num = "";
		}
		num = "";
		$http.get(wsHostUrl + "webapi/ftocservice/"+num)
	    .then(function(response) {
	    	$scope.result = JSON.stringify(response.data);
	    	$scope.checked = true;
	    	console.log(response);
	    	//alert("test");
    	});
	}
    
    
});
//AngularJS Controller.

app.controller('MyCtrl', ['$scope', '$state', 'Page',
  function MyCtrl($scope, $state, Page) {

	$scope.Page = Page;
	
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

    $scope.login = function() {
      $state.go('login').then(function() {
    	  $('body').toggleClass('pushed-right');
          $('#navbar').toggleClass('in');
          Page.setTitle('Log In');
      });      
    };
  }
]);

app.controller('LogInController', ['$scope', '$state', 'Page',
  function LogInController($scope, $state, Page) {
	Page.setTitle('Log In');
  }
]);
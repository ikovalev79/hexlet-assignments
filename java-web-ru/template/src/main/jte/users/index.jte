@import exercise.dto.users.UsersPage
@param UsersPage page

<html>
  	<head>
    	<meta charset="utf-8" />
    	<meta name="viewport" content="width=device-width, initial-scale=1" />
    	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
           	rel="stylesheet"
            integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
            crossorigin="anonymous">
    	<title>Hello Hexlet!</title>
  	</head>
    
    <body>
      <h2>Список пользователей</h2>
    
      @if(page.getUsers().isEmpty())
      	<p>Список пользователей пуст</p>
      @else
        <div>
		  <table width="700" cellspacing="0" cols="4">
            <thead>
              <tr>
                <th>Id</th>
                <th>FirstName</th>
                <th>LastName</th>
                <th>Email</th>
              </tr>
            </thead>
            <tbody>
              @for(var user : page.getUsers())
                <tr>
                  <td><a href="/users/${user.getId()}">${user.getId()}</a></td>
                  <td>${user.getFirstName()}</td>
                  <td>${user.getLastName()}</td>
                  <td>${user.getEmail()}</td>
                </tr>
              @endfor
            </tbody>
          </table>
        </div>
      @endif
    </body>
</html>
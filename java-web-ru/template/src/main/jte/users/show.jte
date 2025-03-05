@import exercise.dto.users.UserPage
@param UserPage page

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
	  <h2>Данные пользователя</h2>
    
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
            <tr>
              <td>${page.getUser().getId()}</td>
              <td>${page.getUser().getFirstName()}</td>
              <td>${page.getUser().getLastName()}</td>
              <td>${page.getUser().getEmail()}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </body>
</html>
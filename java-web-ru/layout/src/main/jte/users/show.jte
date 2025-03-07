@import exercise.dto.users.UserPage
@param UserPage page

@template.layout.page(
    content = @`
        <h2>Данные пользователя</h2>
        
        <div>
    		<table width="500" cellspacing="0" cols="4">
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
    `,
    
    footer = @`
        <p><a href="https://github.com/ikovalev79">Профиль на GitHub</a></p>
    `
)
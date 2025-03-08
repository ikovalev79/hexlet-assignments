@import exercise.dto.users.UsersPage
@param UsersPage page


@template.layout.page(
    content = @`
        <form action="/users" method="get">
  			<input type="search" name="term" value="${page.getTerm()}" />
  			<input type="submit" value="Искать"/>
		</form>
        
        <h2>Список пользователей</h2>
      
      	@if(page.getUsers().isEmpty())
      		<p>Список пользователей пуст</p>
      	@else
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
              		@for(var user : page.getUsers())
                		<tr>
                  			<td>${user.getId()}</td>
                  			<td>${user.getFirstName()}</td>
                  			<td>${user.getLastName()}</td>
                  			<td>${user.getEmail()}</td>
                		</tr>
              		@endfor
            	</tbody>
          		</table>
        	</div>
      	@endif
    `
)
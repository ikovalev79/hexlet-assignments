@import exercise.dto.users.UsersPage
@param UsersPage page

@template.layout.page(
    content = @`
        <h1>Регистрация нового пользователя</h1>

        <form action="/users" method="post">
  			<table >
  			  <tr>
  			    <td>firstName</td>
  			    <td><input type="text" required name="firstName" /></td>
  			  </tr>
  			  <tr>
  			    <td>lastName</td>
  			    <td><input type="text" name="lastName" /></td>
  			  </tr>
  			  <tr>
  			    <td>email</td>
  			    <td><input type="email" required name="email" /></td>
  			  </tr>
  			  <tr>
  			    <td>password</td>
  			    <td><input type="password" required name="password" /></td>
  			  </tr>
  			</table>
  			
  			<input type="submit" value="Зарегистрировать" />
		</form>
    `
)
@import exercise.dto.users.UserPage
@param UserPage page

@template.layout.page(
    content = @`
        <h2>Главная страница</h2>
        <p>Приветствуем на главной странице тестового задания Java: Веб-технологии - Лейауты в JTE</p>
    `,
    
    footer = @`
        <p><a href="https://github.com/ikovalev79">Профиль на GitHub</a></p>
    `
)
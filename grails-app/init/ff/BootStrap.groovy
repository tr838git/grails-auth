package ff

import ff.auth.Role
import ff.auth.User

class BootStrap {

    def init = {
        def roleAdmin = new Role(authority: 'ROLE_ADMIN').save()
        def adminUser = new User(username: 'user', password: 'user').save()

        UserRole.create adminUser, roleAdmin
        UserRole.withTransaction { status ->
            UserRole.withSession {
                it.flush()
                it.clear()
            }
        }
    }

    def destroy = {
    }
}

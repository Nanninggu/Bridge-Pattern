### **\# Bridge-Pattern에 대해 정리.**

### **1\. 아래의 예제 코드로업로드 된 브릿지 패턴의 구조는 아래와 같다.**

브릿지 패턴은 큰 클래스 또는 밀접하게 관련된 클래스들의 로직을 별도의 계층으로 분리하는 구조적 디자인 패턴이다.

이 계층은 추상화와 구현으로 나뉘며, 이들은 서로 독립적으로 개발될 수 있다. 아래에 제공된 코드에서는 User, UserMapper, UserService, UserServiceImpl, 그리고 UserController 클래스들이 브릿지 패턴을 구현하고 있다.  
User 클래스는 추상화를 나타낸다. 이 클래스는 사용자의 정보를 나타내며, 데이터베이스와의 상호작용에 대해서는 알 필요가 없다.  
UserMapper 인터페이스는 구현을 나타낸다. 이 인터페이스는 MyBatis 어노테이션을 사용하여 데이터베이스와의 상호작용을 정의한다.  
UserService 인터페이스는 User 클래스와 UserMapper 인터페이스 사이의 '브릿지' 역할을 한다. 이 인터페이스는 User 클래스가 필요로 하는 메서드를 정의하며, 이 메서드들의 구현은 UserMapper 인터페이스를 통해 이루어진다.  
UserServiceImpl 클래스는 UserService 인터페이스를 구현한다. 이 클래스는 UserMapper 인터페이스를 사용하여 UserService 인터페이스의 메서드를 구현한다.  
마지막으로, UserController 클래스는 HTTP 요청을 처리하고 UserService를 사용하여 이러한 요청에 응답한다. 이 클래스는 UserService와 UserMapper 사이의 '브릿지' 역할을 한다.  
따라서, 이 코드에서는 브릿지 패턴이 UserService와 UserController 클래스를 통해 구현되었으며, 이 패턴을 통해 User 클래스는 데이터베이스와의 상호작용에 대해 알 필요 없이 UserService 인터페이스만을 알면 되며, 이를 통해 User 클래스와 UserMapper 인터페이스 사이의 결합도를 낮추고 코드의 유연성을 높일 수 있다.

### **2\. 핵심 로직 설명**

이 프로젝트에서 브릿지 패턴의 핵심 로직은 `UserService` 인터페이스와 그 구현체인 `UserServiceImpl` 클래스, 그리고 `UserMapper` 인터페이스 사이에서 발생한다.

`UserService` 인터페이스는 추상화를 나타내고, 이 인터페이스는 사용자 정보를 가져오는 `getUser` 메서드를 정의한다. 이 메서드는 사용자의 ID를 입력으로 받아 해당 사용자의 정보를 반환한다.

```
public interface UserService {
    List<User> getUser(String id);
}
```

`UserServiceImpl` 클래스는 `UserService` 인터페이스를 구현하는 클래스로, 이 클래스는 `UserMapper` 인터페이스를 사용하여 `getUser` 메서드를 구현한다. 이 클래스는 `UserService` 인터페이스와 `UserMapper` 인터페이스 사이의 '브릿지' 역할을 한다.

```
@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public List<User> getUser(String id) {
        return userMapper.getUser(id);
    }
}
```

`UserMapper` 인터페이스는 MyBatis 어노테이션을 사용하여 데이터베이스와의 상호작용을 정의하며, 이 인터페이스는 `UserServiceImpl` 클래스에서 사용되며, `UserService` 인터페이스의 `getUser` 메서드의 구현을 담당한다.

```
@Mapper
public interface UserMapper {
    @Select("SELECT * FROM users WHERE id = #{id}")
    List<User> getUser(String id);
}
```

이렇게 `UserService` 인터페이스와 `UserMapper` 인터페이스는 서로 독립적으로 개발될 수 있으며, `UserServiceImpl` 클래스는 이 두 인터페이스를 연결하는 '브릿지' 역할을 한다.

끝

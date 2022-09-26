# Java Test Code 작성법(JUnit5 기준)
Spring 2.2 이상 버전의 SpringBoot 프로젝트 생성시 기본적으로 JUnit5 의존성이 포함된다.

### 의존성 추가(SpringBoot-starter-test 의존성을 사용하지 않는 경우)
#### Maven
```xml
<!-- https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-api -->
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter-api</artifactId>
    <version>5.5.2</version>
    <scope>test</scope>
</dependency>
```
#### Gradle
```groovy
// https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-api
testImplementation group: 'org.junit.jupiter', name: 'junit-jupiter-api', version: '5.5.2'
```

### Test Code 생성 방법(macOS Intellij 기준)
default shortcut 기준으로 `command` + `N`으로 constructor, getter/setter 등을 만들 수 있는데, 이 때 test class도 생성할 수 있다.

### Test 실행 방법(macOS Intellij 기준)
`ctrl` + `shift` + `R`
  * 특정 메서드에 커서가 있는 채로 누를 경우 해당 테스트 메서드만 실행된다.
  * 특정 메서드에 커서가 존재하지 않는 경우 해당 테스트 클래스 전체가 실행된다.

### Basic Annotation
* @Test : 해당 메서드가 테스트 메서드임을 의미(main method가 아니어도 단독 실행이 가능하다.)
* @Disabled : 해당 테스트를 호출되지 않게 한다. (클래스의 테스트 메서드를 모두 실행해도 해당 테스트 메서드는 실행되지 않는다.)
* @BeforeAll : 모든 테스트 메서드가 호출되기 전에 호출된다.
* @AfterAll : 모든 테스트 메서드가 호출된 후에 호출된다.
* @BeforeEach : 모든 테스트 메서드가 호출될 때마다 앞서서 호출된다.
* @AfterEach : 모든 테스트 메서드가 호출될 때마다 따라 호출된다.

### 테스트명 표시하기
#### 1. @DisplayNameGeneration annotation 사용
```java
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class AppTest {
    @Test
    void create_new_app() {
        App app = new App();
        assertNotNull(app);
    }
}
```
위의 경우에는 해당 테스트명이 `create new app`으로 표시되게 된다.

##### 인자로 제공되는 Name Generate 방식 - 기본 구현체로 제공하는 형태
* ReplaceUnderscores : underscore(`_`)를 공백으로 치환한 이름을 만든다.
* IndicativeSentences : 중첩된 클래스 또는 테스트 메서드로부터 지시문 생성(특정 문자열을 `{0}`과 같은 형태로 표시할 수 있다.)하여 이름을 만든다.
  - `@Test` 대신에 `@ParameterizedTest`와 `@ValueSource`을 같이 쓴다.
    ```java
    @DisplayNameGeneration(DisplayNameGenerator.IndicativeSentences)
    class AppTest {
        @ParameterizedTest(name = "Name is {0}")
        @ValueSource(ints = {1, 2, 3})
        void testFunc() {
            // ...
        }
    }
    ```
#### 2. DisplayName
- `@DisplayNameGeneration`보다 우선순위가 높다.
- 띄어쓰기나 한글, 이모지 등을 자유롭게 사용할 수 있다.
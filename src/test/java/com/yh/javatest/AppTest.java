package com.yh.javatest;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class AppTest {

    @Test
    @DisplayName("assertAll로 전체 테스트 묶기 🐶")
    void create_app() {
        App app = new App();
        app.setStatus(AppStatus.STARTED);
        app.setLimit(-10);

        /*
        assertNotNull(app);
        assertEquals(AppStatus.DRAFT, app.getStatus(),
                () -> "스터디를 처음 만들면 상태값이 " + AppStatus.DRAFT + "여야 한다."); // 통과
        assertTrue(app.getLimit() > 0, "스터디 최대 참석 가능 인원은 0보다 커야 한다."); // 실패
         */

        // 위에 주석처리한 코드는 순서대로 테스트가 이루어졌다면, 테스트들을 모두 동시에 실행하는 메서드가 assertAll
        assertAll(
                () -> assertNotNull(app),
                () -> assertEquals(AppStatus.DRAFT, app.getStatus(),
                        () ->"스터디를 처음 만들면 상태값이 " + AppStatus.DRAFT + "여야 한다."),
                () -> assertTrue(app.getLimit() > 0, "스터디 최대 참석 가능 인원은 0보다 커야 한다.")
        );
    }

    @Test
    @DisplayName("assertThrows 사용해보기 🐶")
    void create_app_2() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new App(-10));
        String message = exception.getMessage();
        assertEquals("limit은 0보다 커야 할까?", message);
    }

    @Test
    @DisplayName("timeout 사용해보기 🐶")
    void create_app_3() {
        assertTimeout(Duration.ofMillis(100), () -> {
            new App(10);
            Thread.sleep(300);
        });

        assertTimeoutPreemptively(Duration.ofMillis(100), () -> {
            new App(10);
            Thread.sleep(300);
        });
    }

    @Test
    @Disabled
    @DisplayName("Disabled된 테스트 메서드 🐶")
    void create_app_4() {
    }

    @BeforeAll
    @DisplayName("before all 🥚")
    static void beforeAll() {
    }

    @AfterAll
    @DisplayName("after all 🐔")
    static void afterAll() {
    }

    @BeforeEach
    @DisplayName("before each 🐤")
    void beforeEach() {
    }

    @AfterEach
    @DisplayName("after each 🐤")
    void afterEach() {
    }


}

https://advancedweb.hu/new-language-features-since-java-8-to-21/
https://advancedweb.hu/a-categorized-list-of-all-java-and-jvm-features-since-jdk-8-to-21/

### JUnit 5

#### Annotations:
- @BeforeEach
- @AfterEach
- @BeforeAll
- @AfterAll
- @Test
- @ParameterizedTest
  @ValueSource(ints = {1, 3, 5, -3, 15, Integer.MAX_VALUE}) // six numbers(It can be array of either primitive, String or Class)
  void isOdd_ShouldReturnTrueForOddNumbers(int number) {
  assertTrue(Numbers.isOdd(number));
  }
- 
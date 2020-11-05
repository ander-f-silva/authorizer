package br.com.nb.authorizer.wrapper;

import br.com.nb.authorizer.wrapper.mock.BirthDay;
import br.com.nb.authorizer.wrapper.mock.DateMock;
import br.com.nb.authorizer.wrapper.mock.InputMock;
import br.com.nb.authorizer.wrapper.mock.OutputMock;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class JsonMapperTest {
  @Test
  @DisplayName("should mapper json to object")
  public void testMapperJsonToObject() {
    var jsonMapper = new JsonMapper<InputMock>();

    var input = jsonMapper.mapperJsonToObject("{\"name\":\"Peter\",\"age\":12}", InputMock.class);

    var expected = new InputMock("Peter", 12);

    assertNotNull(input);
    assertEquals(expected, input);
  }

  @Test
  @DisplayName("should mapper object to json")
  public void testMapperObjectToJson() {
    var jsonMapper = new JsonMapper<OutputMock>();

    var output =
        jsonMapper.mapperObjectToJson(new OutputMock("Peter Jordan", "Black House, number 33"));

    var expected = "{\"fullName\":\"Peter Jordan\",\"address\":\"Black House, number 33\"}";

    assertNotNull(output);
    assertEquals(expected, output);
  }

  @Test
  @DisplayName("should mapper object with attribute of the type date to json")
  public void testMapperJsonToDateType() {
    var birthDay = new DateMock(BirthDay.createDate());

    var jsonMapper = new JsonMapper<DateMock>();

    var input =
        jsonMapper.mapperJsonToObject(
            "{\"birthDay\":\"2019-02-13T10:00:00.000Z\"}", DateMock.class);

    var expected = birthDay;

    assertNotNull(input);
    assertEquals(expected, input);
  }

  @Test
  @DisplayName("should mapper json to with object with attribute of the type date")
  public void testMapperDateTypeToJson() {
    var jsonMapper = new JsonMapper<DateMock>();

    var output = jsonMapper.mapperObjectToJson(new DateMock(BirthDay.createDate()));

    var expected = "{\"birthDay\":\"2019-02-13T10:00:00.000Z\"}";

    assertNotNull(output);
    assertEquals(expected, output);
  }
}

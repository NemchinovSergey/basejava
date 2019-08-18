# Web приложение "База данных резюме" по [курсу BaseJava](http://javaops.ru/reg/basejava)

> Любое знание стоит воспринимать как подобие семантического дерева: убедитесь в том, что понимаете фундаментальные принципы, то есть ствол и крупные ветки, прежде чем лезть в мелкие листья-детали. Иначе последним не на чем будет держаться.

[*— Илон Маск](https://ru.wikipedia.org/wiki/Маск,_Илон)

## Реализация
  - используется: Java 8, IntelliJ IDEA, GitHub/Git, Сервлеты, JSP, JSTL, Tomcat, JUnit, PostgreSQL, GSON, JAXB
  - хранение данных
     -  в памяти на основе массива, отсортированного массива, списка и ассоциированного массива (Map)
     -  в файловой системе (File API и <a href="http://www.quizful.net/post/java-nio-tutorial">Java 7 NIO File API</a>)
        - в стандартной и кастомной сериализации Java
        - в формате JSON (<a href="https://github.com/google/gson">Google Gson</a>)
        - в формате XML (<a href="https://ru.wikipedia.org/wiki/Java_Architecture_for_XML_Binding">JAXB</a>)
     -  в реляционной базе <a href="https://ru.wikipedia.org/wiki/PostgreSQL">PostgreSQL</a>
  - деплой веб приложения
     - в контейнер сервлетов <a href="http://tomcat.apache.org/">Tomcat</a>
     - в облачный сервис <a href="https://www.heroku.com/">Heroku</a>

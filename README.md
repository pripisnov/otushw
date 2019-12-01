**Курс "Разработчик Java" в OTUS**
 
 Группа 2019-09
 
 Студент
**Pripisnov Andrey** (Приписнов Андрей)
pripisnov@bk.ru

***ДЗ: hw01-maven*** Проект с модульной структурой:

 - Создать проект maven;
 - Создать модуль hw01-maven;
 - Определить необходимые зависимости:
    - guava;
 - Собрать jar с зависимостями ("толстый" jar).
 
 ***ДЗ: hw03-collection*** DIY ArrayList, работоспособность методов для size >= 20:
 
 - Collections.addAll(Collection<? super T> c, T... elements);
 - Collections.static <T> void copy(List<? super T> dest, List<? extends T> src);
 - Collections.static <T> void sort(List<T> list, Comparator<? super T> c).
 
 ***ДЗ: hw10-framework*** поддержка аннотаций @Test, @Before, @After:
 - Создать аннотации  @Test, @Before, @After;
 - Создать запуск теста по имени класса с тестами;
 - На основании исключений, возникших во время тестирования, вывести статистику выполенния тестов (started, failed, passed)
 
 ***ДЗ: hw12-amt*** концепты проектирования ООП, эмулятор АМТ:
 - АМТ принимает банкноты различных номиналов;
 - АМТ выдает сумму минимальным количеством банкнот или ошибку, если сумму нельзя выдать;
 - АМТ выдает остаток денежных средств.
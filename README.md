# File handling API

### API
1. __POST__ `/file/removeStyles` 
Принимает на вход ворд документ в формате docx. 
Выставляет всем элементам в файле стиль форматирования "Обычный" ("Normal"). 
Сохраняет запись о проведенной операции в БД.
Возвращает измененный документ. 

2. __GET__ `/report/removeStyles` 
Принимает на вход две даты ("с" и "по") и тип сортировки (asc / desc).
Если входящие параметры не указаны, то вместо них подставляются значения по умолчанию.
Ищет в БД записи о проведенных операциях опираясь на входящие данные и возвращает их.


### Заметки
- Используется БД H2, первоначальные настройки можно найти в файле `/resources/data.sql`.
- Стилистика наименований в БД.
    - префиксы перед таблицами, сиквенсами, функциями, тригерами и тд, чтобы лучше понимать по логам из БД о чем идет речь.
    - первая буква в названии колонки говорит о типе данных в колонке.
    - не люблю нижние подчеркивания и кэмелкейс в названиях в БД, очень сложно печатать в функциях и процедурах.
- Залил в БД пару записей с прошедшими датами, чтобы можно было проверить фильтрацию по датам в эндпоинте `/report/removeStyles`.
- В эндпоинте `/report/removeStyles` во входящем параметре можно было бы использовать спринговый энум Sort.Directions вместо самописного энума SortDirection, но в этом случае мы бы лишились возможности кастомных сортировок в будущем (Из серии - хочу чтобы проплаченные буржуи были на первых позициях, а дальше по desc. И назовем этот тип сортировки PAYED_FIRST). 
- Можно было бы и не делать модельки для ответов в этом примере, но в реальном приложении то мы в любом случае будем показывать не все данные из БД (все зависит от бизнес логики конечно).

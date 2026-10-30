# Темы и тёмный режим

## Модель токенов

Единый источник токенов нативного направления — `lh-ui-core/tokens/*.json`, codegen генерирует Android-артефакты:

| Артефакт | Содержимое | Тёмная форма |
| --- | --- | --- |
| `LhTokens` | Полный светлый набор: размеры Button, палитра Color, Motion, Radius, Shadow, Spacing, Typography, ZIndex | — |
| `LhDarkTokens` | Только подобъект `Color` (модель **параллельных объектов**) | Тёмный режим переопределяет только цвета; размеры, анимации и скругления в тёмном режиме не меняются |

> Так же, как в ios / harmony / windows (параллельные объекты), в отличие от сайта документации (поверхностное слияние JSON).
> Тематический слой спроектирован соответственно: `LhColors` хранит только цвета, а размеры компоненты берут напрямую из `LhTokens`.

## LhColors

`LightLhColors` / `DarkLhColors` разворачивают два сгенерированных object в одну неизменяемую палитру:

```kotlin
@Immutable
data class LhColors(
  val primary: Color, val onPrimary: Color,
  val success: Color, val warning: Color, val danger: Color, val info: Color,
  val textPrimary: Color, val textRegular: Color, val textSecondary: Color, /* ... */
)

val LightLhColors = LhColors(primary = LhTokens.Color.Primary.default, /* ... */)
val DarkLhColors = LhColors(primary = LhDarkTokens.Color.Primary.default, /* ... */)
```

## LhTheme

```kotlin
LhTheme(isDark = true) {
  // LocalLhColors внедряет DarkLhColors, компоненты внутри потребляют его автоматически
}
```

Без обёртки `LhTheme` используется откат на `LightLhColors` (по умолчанию светлый режим).

## Предвычисление тёмного режима (важно)

В направлении web тёмный режим вычисляется динамически через CSS `color-mix()`, но в нативном нет CSS-каскада, поэтому core **предвычислил тёмные выражения в литералы** (например `rgba(61,100,158,0.25)`, `#B1C1D8`). Следствие (генеральный план R-n6):

> **При изменении основного цвета пресета предвычисленные тёмные значения не обновляются автоматически** — их нужно пересчитать вручную и обновить
> `lh-ui-core/tokens/color-dark.json`, затем обновить *Базовый стандарт языка дизайна*.

## Справочник токенов

Полная таблица токенов (светлый/тёмный/описание) — в [справочнике токенов lh-ui-core](https://www.lhgb.top/lhui/core/tokens/),
либо в `dist/docs/tokens.md` после запуска `pnpm codegen` в репозитории core. Отображение токенов на уровне компонентов — в
[справочнике токенов кнопки Button](/ru/components/button#справочник-токенов).

## Пользовательские пресеты (зарезервировано)

`LhTheme(colors = ...)` принимает пользовательскую палитру; возможность пресетов темы будет открыта в следующих версиях вместе с токенами пресетов темы из core.

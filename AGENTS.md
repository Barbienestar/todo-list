# AGENTS.md - scholarly-atelier

## Dev Commands
```bash
./mvnw quarkus:dev          # Dev mode with live reload
./mvnw test                # Run tests
./mvnw package             # Build JAR
```

## Architecture
- **Quarkus 3.34.3** with REST, Hibernate ORM Panache, MySQL, Firebase
- **Layers**: domain → application → infrastructure → interfaces
- **Entry point**: `src/main/java/com/itesm/interfaces/rest/`

## Quarkus Panache Gotcha
When implementing `PanacheRepositoryBase`, the generic type MUST be the Entity (`*Entity`), NOT the Domain model.

**Wrong**:
```java
public class CategoryRepositoryImpl implements CategoryRepository, PanacheRepositoryBase<Category, UUID>
```

**Correct**:
```java
public class CategoryRepositoryImpl implements CategoryRepository, PanacheRepositoryBase<CategoryEntity, UUID>
```

Using the wrong generic causes `firstResultOptional().map()` to fail because the query returns the wrong type.

## Repositories with Domain Models
For repositories that return domain models (not entities), manually query and map:
```java
public Optional<Category> find(UUID id) {
    return find("id", id).firstResultOptional().map(CategoryMapper::toDomain);
}
```

## Firebase Setup
Credentials required in: `src/main/resources/todolist1-a1996-firebase-adminsdk-fbsvc-c6c56ce491.json`
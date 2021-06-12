package hello.core.member;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class Member {
    private final Long id;
    private final String name;
    private final Grade grade;
}

package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.model.TodoEntity;
import org.example.model.TodoRequest;
import org.example.model.TodoResponse;
import org.example.service.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

// 서버에 들어온 요청을 받는 부분
// 요청에 따라서 어떤 작업을 처리하고 어떤 응답을 내려줄 지 구현

@CrossOrigin // CURS 라는 이슈를 받기 위해서
@AllArgsConstructor
@RestController
@RequestMapping("/")
public class TodoController {
    private final TodoService todoService;

    // 생성
    @PostMapping
    public ResponseEntity<TodoResponse> create(@RequestBody TodoRequest request) {
        System.out.println("CREATE");

        // 값이 있는지 없는지 체크
        if (ObjectUtils.isEmpty(request.getTitle())) {
            return ResponseEntity.badRequest().build();
        }

        // Order나 Completed값도 넘어오지 않을 수 있음
        // 그럴 때는 기본값으로 지정
        if (ObjectUtils.isEmpty(request.getOrder())) {
            request.setOrder(0L);
        }
        if (ObjectUtils.isEmpty(request.getCompleted())) {
            request.setCompleted(false);
        }

        TodoEntity result = this.todoService.add(request);

        return ResponseEntity.ok(new TodoResponse(result));
    }

    // 하나만 조회
    // 경로에 id값을 받아오기
    @GetMapping("{id}")
    public ResponseEntity<TodoResponse> readOne(@PathVariable Long id) {
        System.out.println("READ ONE");

        TodoEntity result = this.todoService.searchById(id);

        return ResponseEntity.ok(new TodoResponse(result));
    }

    // 전체 조히
    @GetMapping
    public ResponseEntity<List<TodoResponse>> readAll() {
        System.out.println("READ ALL");

        List<TodoEntity> list = this.todoService.searchAll();
        List<TodoResponse> response = list.stream().map(TodoResponse::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    // 수정
    // (@PutMapping도 수정)
    // PUT은 모든 것을 업데이트, PATCH는 일부를 업데이트
    @PatchMapping("{id}")
    public ResponseEntity<TodoResponse> update(@PathVariable Long id, @RequestBody TodoRequest request) {
        System.out.println("UPDATE");

        TodoEntity result = this.todoService.updateById(id, request);

        return ResponseEntity.ok(new TodoResponse(result));
    }

    // 하나만 삭제
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteOne(@PathVariable Long id) {
        System.out.println("DELETE");

        this.todoService.deleteById(id);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAll() {
        System.out.println("DELETE ALL");

        this.todoService.deleteAll();

        return ResponseEntity.ok().build();
    }

}

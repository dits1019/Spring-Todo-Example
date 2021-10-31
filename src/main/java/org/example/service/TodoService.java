package org.example.service;

import lombok.AllArgsConstructor;
import org.example.model.TodoEntity;
import org.example.model.TodoRequest;
import org.example.repository.TodoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    // todo 리스트 목록에 아이템을 추가
    public TodoEntity add(TodoRequest request) {
        TodoEntity todoEntity = new TodoEntity();
        todoEntity.setTitle(request.getTitle());
        todoEntity.setOrder(request.getOrder());
        todoEntity.setCompleted(request.getCompleted());

        return this.todoRepository.save(todoEntity);
    }

    // todo 리스트 목록 중 특정 아이템을 조회
    public TodoEntity searchById(Long id) {
        return this.todoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)); // 값이 없을 때 Not Found Exception을 보냄
    }

    // todo 리스트 목록 전체 목록을 조회
    public List<TodoEntity> searchAll() {
        return this.todoRepository.findAll();
    }

    // todo 리스트 목록 중 특정 아이템을 수정
    public TodoEntity updateById(Long id, TodoRequest todoRequest) {
        // 먼저 특정 아이템을 가져오기
        TodoEntity todoEntity = this.searchById(id);

        // 그 후 각각 null이 아니라면 값을 변경
        if(todoRequest.getTitle() != null)
            todoEntity.setTitle(todoRequest.getTitle());

        if(todoRequest.getOrder() != null)
            todoEntity.setOrder(todoRequest.getOrder());

        if(todoRequest.getCompleted() != null)
            todoEntity.setCompleted(todoRequest.getCompleted());

        return this.todoRepository.save(todoEntity);
    }

    // todo 리스트 목록 중 특정 아이템을 삭제
    public void deleteById(Long id) {
        this.todoRepository.deleteById(id);
    }

    // todo 리스트 전체 목록을 삭제
    public void deleteAll() {
        this.todoRepository.deleteAll();
    }

}
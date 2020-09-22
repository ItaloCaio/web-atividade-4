package br.com.web.endpoint;

import br.com.web.error.CustomErrorType;
import br.com.web.error.ResourceNotFoundException;
import br.com.web.model.Turma;
import br.com.web.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("turmas")
public class TurmaEndPoint {

    private final TurmaRepository turmaRepository;

    @Autowired
    public TurmaEndPoint(TurmaRepository turmaRepository){
        this.turmaRepository = turmaRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> listAll() {
        return new ResponseEntity<>(turmaRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getClassById(@PathVariable("id") long id) {
        verifyIfTurmaExists(id);
        Turma aClass = turmaRepository.findOne(id);
        if (aClass == null) {
            return new ResponseEntity<>(new CustomErrorType("Class not found "), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(aClass, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Turma turma) {

        return new ResponseEntity<>(turmaRepository.save(turma), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        verifyIfTurmaExists(id);
        turmaRepository.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Turma turma) {
        verifyIfTurmaExists(turma.getId());
        turmaRepository.save(turma);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    private void verifyIfTurmaExists(Long id){
        if (turmaRepository.findOne(id) == null)
            throw new ResourceNotFoundException("Turma not found for ID: " + id);
    }

}

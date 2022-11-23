package Eva.Baigiamasis_darbas.controllers;

import Eva.Baigiamasis_darbas.converter.RegistrationFormsOfPersonsConverter;
import Eva.Baigiamasis_darbas.dto.CreateRegistrationFormsOfPersonsRequestDTO;
import Eva.Baigiamasis_darbas.dto.GetRegistrationFormsOfPersonsResponseDTO;
import Eva.Baigiamasis_darbas.entities.RegistrationFormsOfPersons;
import Eva.Baigiamasis_darbas.services.RegistrationFormsOfPersonsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/RegistrationFormsOfPersons")
public class RegistrationFormsOfPersonsController {

    @Autowired
    private RegistrationFormsOfPersonsService registrationFormsOfPersonsService;

    @GetMapping()
    public List<RegistrationFormsOfPersons> getAllPersons() {
        return registrationFormsOfPersonsService.getAllPersons();
    }

    @PostMapping
    public void addRegistrationFormsOfPersons(@RequestBody CreateRegistrationFormsOfPersonsRequestDTO createRegistrationFormsOfPersonsRequestDTO) {
        RegistrationFormsOfPersons registrationFormsOfPersons = RegistrationFormsOfPersonsConverter.convertCreateRegistrationFormsOfPersonsRequestDtoToRegistrationFormsOfPersons(createRegistrationFormsOfPersonsRequestDTO);
        this.registrationFormsOfPersonsService.createPersons(registrationFormsOfPersons);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> putPersonById(@PathVariable(name = "id") Long id, @RequestBody CreateRegistrationFormsOfPersonsRequestDTO requestDTO) {

        RegistrationFormsOfPersons person = this.registrationFormsOfPersonsService.getPersonById(id);
        if (person == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        RegistrationFormsOfPersons newpersons = RegistrationFormsOfPersonsConverter.convertCreateRegistrationFormsOfPersonsRequestDtoToRegistrationFormsOfPersons(requestDTO);
        newpersons.setId(person.getId());
        this.registrationFormsOfPersonsService.saveRegistrationFormsOfPersons(newpersons);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> patchPersonById(@PathVariable(name = "id") Long id, @RequestBody CreateRegistrationFormsOfPersonsRequestDTO requestDTO) {

        RegistrationFormsOfPersons person = this.registrationFormsOfPersonsService.getPersonById(id);
        if (person == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        RegistrationFormsOfPersonsConverter.patchRegistrationFormsOfPersonsFromCreateRegistrationFormsOfPersonsRequestDto(person, requestDTO);
        this.registrationFormsOfPersonsService.saveRegistrationFormsOfPersons(person);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/{id}")
    public ResponseEntity<GetRegistrationFormsOfPersonsResponseDTO> getPersonById(@PathVariable(name = "id") Long id) {
        RegistrationFormsOfPersons person = this.registrationFormsOfPersonsService.getPersonById(id);
        if (person == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return ResponseEntity.ok(RegistrationFormsOfPersonsConverter.convertRegistrationFormsOfPersonsToGetRegistrationFormsOfPersonsResponseDto(person));
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersonById(@PathVariable(name = "id") Long id) {
        this.registrationFormsOfPersonsService.deletePersonById(id);
            return ResponseEntity.ok().build();
        }



}

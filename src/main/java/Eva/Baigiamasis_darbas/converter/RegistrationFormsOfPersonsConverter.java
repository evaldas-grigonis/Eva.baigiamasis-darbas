package Eva.Baigiamasis_darbas.converter;

import Eva.Baigiamasis_darbas.dto.CreateRegistrationFormsOfPersonsRequestDTO;
import Eva.Baigiamasis_darbas.dto.GetRegistrationFormsOfPersonsResponseDTO;
import Eva.Baigiamasis_darbas.entities.RegistrationFormsOfPersons;

import java.time.LocalDate;

public class RegistrationFormsOfPersonsConverter {

    public static GetRegistrationFormsOfPersonsResponseDTO convertRegistrationFormsOfPersonsToGetRegistrationFormsOfPersonsResponseDto(RegistrationFormsOfPersons registrationFormsOfPersons) {
        GetRegistrationFormsOfPersonsResponseDTO registrationFormsOfPersonsDTO = null;
        if (registrationFormsOfPersons != null){
            registrationFormsOfPersonsDTO = new GetRegistrationFormsOfPersonsResponseDTO();
            registrationFormsOfPersonsDTO.setId(registrationFormsOfPersons.getId());
            registrationFormsOfPersonsDTO.setName(registrationFormsOfPersons.getName());
            registrationFormsOfPersonsDTO.setLastName(registrationFormsOfPersons.getLastName());
            registrationFormsOfPersonsDTO.setEmail(registrationFormsOfPersons.getEmail());
            registrationFormsOfPersonsDTO.setDateOfBirth(registrationFormsOfPersons.getDateOfBirth());

        }
        return registrationFormsOfPersonsDTO;
    }

    public static RegistrationFormsOfPersons convertCreateRegistrationFormsOfPersonsRequestDtoToRegistrationFormsOfPersons(CreateRegistrationFormsOfPersonsRequestDTO requestDTO){
        RegistrationFormsOfPersons registrationFormsOfPersons = null;
        if (requestDTO != null){
            registrationFormsOfPersons = new RegistrationFormsOfPersons();
            registrationFormsOfPersons.setName(requestDTO.getName());
            registrationFormsOfPersons.setLastName(requestDTO.getLastName());
            registrationFormsOfPersons.setEmail(requestDTO.getEmail());
            registrationFormsOfPersons.setDateOfBirth(requestDTO.getDateOfBirth());
        }
        return registrationFormsOfPersons;
    }
    public static  RegistrationFormsOfPersons patchRegistrationFormsOfPersonsFromCreateRegistrationFormsOfPersonsRequestDto(RegistrationFormsOfPersons registrationFormsOfPersons,
                                                                                                                            CreateRegistrationFormsOfPersonsRequestDTO requestDTO){

        if (isNewStringValueEmptyNullOrSameAsOld(requestDTO.getName(), registrationFormsOfPersons.getName())) {
            registrationFormsOfPersons.setName(requestDTO.getName());
        }

        if (isNewStringValueEmptyNullOrSameAsOld(requestDTO.getLastName(), registrationFormsOfPersons.getLastName())) {
            registrationFormsOfPersons.setLastName(requestDTO.getLastName());
        }

        if (isNewStringValueEmptyNullOrSameAsOld(requestDTO.getEmail(), registrationFormsOfPersons.getEmail())) {
            registrationFormsOfPersons.setEmail(requestDTO.getEmail());
        }
        if (isNewLocalDateValueEmptyNullOrSameAsOld(requestDTO.getDateOfBirth(), registrationFormsOfPersons.getDateOfBirth())) {
            registrationFormsOfPersons.setDateOfBirth(requestDTO.getDateOfBirth());
        }
        return registrationFormsOfPersons;
    }
    private static boolean isNewStringValueEmptyNullOrSameAsOld(String newValue, String oldValue){
        return newValue != null && !newValue.isEmpty() && !newValue.equals(oldValue);
    }
    private static boolean isNewLocalDateValueEmptyNullOrSameAsOld(LocalDate newValue, LocalDate oldValue){
        return newValue != null  && !newValue.equals(oldValue);
    }

}

@Component
@Slf4j
@AllArgsConstructor
public class VacanciesService {
   @Value("vendor.host")
   String vendorHost;
   @Value("vendor.uri")
   String vendorUri;
   @Autowired
   private RestTemplate template;
   private Map<Long, List<VacancyHH>> cache;

   List<VacancyHeadHunter> getList(Long page, Long size, List<String> types, List<String> companies) {
       if (cache.containsKey(getHash(page, types, companies))) {
           return cache.get(getHash(page, types, companies));
       }
       try {
           List<VacancyHeadHunter> vacancies = template.getForObject(vendorHost + vendorUri, VacanciesHeadHunter.class,
                   page, size, types, companies
           ).getVacancies();
           cache.put(getHash(page, types, companies), vacancies);
           return vacancies;
       } catch (Exception e) {
           cache.put(getHash(page, types, companies), null);
           return null;
       }
   }
}

public class HashUtils {
   public static Long getHash(Long page, List<String> types, List<String> companies) {
       return 1l;
   }
}

@Data
public class VacanciesHeadHunter {
   private List<VacancyHeadHunter> vacancies;
}

public class VacancyHeadHunter {
}

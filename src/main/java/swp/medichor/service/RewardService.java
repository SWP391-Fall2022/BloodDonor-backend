package swp.medichor.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp.medichor.model.Reward;
import swp.medichor.model.response.EarnedRewardResponse;
import swp.medichor.model.response.RewardResponse;
import swp.medichor.repository.EarnedRewardRepository;
import swp.medichor.repository.RewardRepository;

@Service
public class RewardService {

    @Autowired
    private RewardRepository rewardRepository;
    @Autowired
    private EarnedRewardRepository earnedRewardRepository;
    @Autowired
    private DonorService donorService;

    public List<RewardResponse> getAll() {
        return rewardRepository.findAll()
                .stream()
                .map(reward -> new RewardResponse(reward))
                .collect(Collectors.toList());
    }

    public List<EarnedRewardResponse> getEarned(int donorId) {
        return earnedRewardRepository.findAllById_DonorId(donorId)
                .stream()
                .map(earned -> new EarnedRewardResponse(earned))
                .collect(Collectors.toList());
    }

    public List<RewardResponse> getAllAvailable(int donorId) {
        List<Reward> earned = earnedRewardRepository.findAllById_DonorId(donorId)
                .stream()
                .map(earnedReward -> earnedReward.getReward())
                .collect(Collectors.toList());

        List<RewardResponse> available = rewardRepository.findAll()
                .stream()
                .filter(reward -> reward.getStatus() == true
                && reward.getAmount() > 0
                && reward.getExpiredDate().compareTo(new Date(LocalDate.now().toEpochDay())) >= 0
                && reward.getLevel() <= donorService.getPoints(donorId)
                && !earned.contains(reward))
                .map(reward -> new RewardResponse(reward))
                .collect(Collectors.toList());
        
        return available;
    }
}

package swp.medichor.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp.medichor.model.Reward;
import swp.medichor.model.request.RewardRequest;
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
                && reward.getExpiredDate().compareTo(Date.valueOf(LocalDate.now())) >= 0
                && reward.getLevel() <= donorService.getPoints(donorId)
                && !earned.contains(reward))
                .map(reward -> new RewardResponse(reward))
                .collect(Collectors.toList());

        return available;
    }

    public RewardResponse getById(int id) {
        Optional<Reward> reward = rewardRepository.findById(id);
        if (reward.isPresent()) {
            return new RewardResponse(reward.get());
        } else {
            return null;
        }
    }

    public int addReward(RewardRequest request) {
        Reward newReward = rewardRepository.save(Reward.builder()
                .expiredDate(request.getExpiredDate())
                .level(request.getLevel())
                .sponsor(request.getSponsor())
                .code(request.getCode())
                .status(request.getStatus())
                .details(request.getDetails())
                .amount(request.getAmount())
                .build());
        return newReward.getId();
    }

    public void updateReward(int id, RewardRequest request) {
        rewardRepository.findById(id)
                .ifPresentOrElse(reward -> {
                    reward.setExpiredDate(request.getExpiredDate());
                    reward.setLevel(request.getLevel());
                    reward.setSponsor(request.getSponsor());
                    reward.setCode(request.getCode());
                    reward.setDetails(request.getDetails());
                    reward.setAmount(request.getAmount());
                    rewardRepository.save(reward);
                }, () -> {
                    throw new RuntimeException("Mã khuyến mãi không tồn tại");
                });
    }

    public void disableReward(int id) {
        rewardRepository.findById(id)
                .ifPresentOrElse(reward -> {
                    reward.setStatus(false);
                    rewardRepository.save(reward);
                }, () -> {
                    throw new RuntimeException("Mã khuyến mãi không tồn tại");
                });
    }
}

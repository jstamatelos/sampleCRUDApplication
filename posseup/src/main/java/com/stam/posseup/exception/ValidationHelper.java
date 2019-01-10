package com.stam.posseup.exception;

import com.stam.posseup.entity.Member;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.stam.posseup.positions.PositionConstants.LEADER;
import static com.stam.posseup.positions.PositionConstants.MEMBER;
import static com.stam.posseup.positions.PositionConstants.PROSPECT;

public class ValidationHelper {

    private static final Logger logger = LoggerFactory.getLogger(ValidationHelper.class);

    /**
     * @param newMember -> Helper method to complete validation on new member position
     *                  Valid values are leader, member, prospect
     */
    public static void validateNewMemberPositionAndName(Member newMember) {
        if (!newMember.getPosition().equalsIgnoreCase(LEADER) && !newMember.getPosition().equalsIgnoreCase(MEMBER) && !newMember.getPosition().equalsIgnoreCase(PROSPECT)) {
            logger.info("Validating position chosen :: {} ", newMember.getPosition());
            throw new MemberPositionException(newMember.getPosition());

        }

    }
}

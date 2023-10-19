package goope.rimu.caloriefittrainer.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import goope.rimu.caloriefittrainer.R
import goope.rimu.caloriefittrainer.model.TrainingModel

class TrainingViewModel(application: Application) : AndroidViewModel(application) {
    val context = application

    val trainingList = mutableListOf(
        TrainingModel(
            imageUrl = "https://resize.indiatvnews.com/en/resize/newbucket/1200_-/2016/07/walking-1000x600-1468929054.jpg",
            title = context.getString(R.string.title1),
            desc = context.getString(R.string.desc1),
            lessons = 5,
            urlReference = "https://youtu.be/njeZ29umqVE?list=PL-8fyND0sPmMnE5nAgZrL7EbI_a7BIFQB",
            kcal = "250-300",
            timeDuration = context.getString(R.string._30_60_minutes)
        ),
        TrainingModel(
            imageUrl = "https://c.ndtvimg.com/2020-08/dtm9edd8_cycling_625x300_05_August_20.jpg?im=Resize=(1230,900)",
            title = context.getString(R.string.title2),
            desc = context.getString(R.string.desc2),
            lessons = 12,
            urlReference = "https://youtu.be/DE5DYJGWDpU?list=PLUdAMlZtaV13PdYsFF89Qt7m8eIeNdmyk",
            kcal = "200-250",
            timeDuration = context.getString(R.string._30_60_minutes)
        ),
        TrainingModel(
            imageUrl = "https://d1s9j44aio5gjs.cloudfront.net/2016/07/The_Benefits_of_Swimming.jpg",
            title = context.getString(R.string.title3),
            desc = context.getString(R.string.desc3),
            lessons = 5,
            urlReference = "https://youtu.be/gh5mAtmeR3Y?list=PLP1qQyYhQ-zNuocUdJ4UMYERs9Enu3Z0R",
            kcal = "300-350",
            timeDuration = context.getString(R.string._30_60_minutes)
        ),
        TrainingModel(
            imageUrl = "https://static.cdntap.com/tap-assets-prod/wp-content/uploads/sites/24/2021/08/yoga-9.jpg",
            title = context.getString(R.string.title4),
            desc = context.getString(R.string.desc4),
            lessons = 11,
            urlReference = "https://youtu.be/GLy2rYHwUqY?list=PLui6Eyny-Uzwzd-9fi_cmhz3UW9gS1raf",
            kcal = "100-150",
            timeDuration = context.getString(R.string._30_minutes)
        ),
        TrainingModel(
            imageUrl = "https://focusphysicaltherapyscv.com/wp-content/uploads/2019/03/AdobeStock_132111328.jpeg",
            title = context.getString(R.string.title5),
            desc = context.getString(R.string.desc5),
            lessons = 1,
            urlReference = "https://youtu.be/FI51zRzgIe4",
            kcal = "100-150",
            timeDuration = context.getString(R.string._30_minutes)
        )
    )
}